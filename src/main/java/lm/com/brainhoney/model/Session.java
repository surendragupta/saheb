package lm.com.brainhoney.model;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Session {

	// The user agent for this application
    // You should set this to identify the calling application for logging
    private String fAgent;
    // The URL to the DLAP server
    private String fServer;
    // Request timeout in milliseconds
    // Defaults to 30000 (30 seconds)
    private int fTimeout;
    // Outputs all requests and responses to the console for trouble shooting
    private Boolean fVerbose;
    
    private File file;
    private String jsonOmittedList;

    private Transformer fTransformer = null;
    private DocumentBuilder fDocBuilder = null;
    
    // Create a new session object
    public Session(String agent, String server) throws TransformerConfigurationException, ParserConfigurationException
    {
        fAgent = agent;
        fServer = server;
        fTimeout = 30000;
        fVerbose = false;

        CreateTransformerAndDocumentBuilder();
    }

    // Create a new session object
    public Session(String agent, String server, int timeout, Boolean verbose) throws TransformerConfigurationException, ParserConfigurationException
    {
        fAgent = agent;
        fServer = server;
        fTimeout = timeout;
        fVerbose = verbose;

        CreateTransformerAndDocumentBuilder();
    }

    private void CreateTransformerAndDocumentBuilder() throws TransformerConfigurationException, ParserConfigurationException
    {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        fTransformer = transformerFactory.newTransformer();
        fTransformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        fTransformer.setOutputProperty(OutputKeys.METHOD, "xml");
        fTransformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        fDocBuilder = docFactory.newDocumentBuilder();
    }

    // Login to DLAP. Call Logout to close session.
    public Document Login(String prefix, String username, String password)
    throws ParserConfigurationException, TransformerException, IOException, SAXException
    {
            Document doc = fDocBuilder.newDocument();
            Element rootElement = doc.createElement("request");
            doc.appendChild(rootElement);
            rootElement.setAttribute("cmd", "login");
            rootElement.setAttribute("username", prefix + "/" + username);
            rootElement.setAttribute("password", password);

            return Post(null, doc);
    }
    
 // Logout of DLAP
    public Document Logout()
    throws TransformerException, IOException, ParserConfigurationException, SAXException
    {
        Document result = Get("logout", null);
        return result;
    }

    // Makes a GET request to DLAP
    public Document Get(String cmd, Map<String, String> parameters)
    throws TransformerException, IOException, ParserConfigurationException, SAXException
    {
        String query = "?cmd=" + cmd;
        if(parameters != null)
        {
            for (String key : parameters.keySet())
            {
                query += "&" + key + "=" + parameters.get(key);
            }
        }
        return Request(query, null);
    }

    // Makes a POST request to DLAP
    public Document Post(String cmd, Document xml)
    throws TransformerException, IOException, ParserConfigurationException, SAXException
    {
        String query = (cmd == null || cmd.length() == 0) ? "" : ("?cmd=" + cmd);
        return Request(query, xml);
    }

    // Makes a raw request to DLAP
    private Document Request(String query, Document postData)
    throws IOException, ParserConfigurationException, SAXException, TransformerException
    {
        if (fVerbose)
        {
            System.out.println();
            System.out.println("Request: " + fServer + query);
            if(postData != null)
            {
                StreamResult result = new StreamResult(System.out);
                DOMSource source = new DOMSource(postData);
                fTransformer.transform(source, result);
                System.out.println();
            }
            System.out.println();
        }

        URL url = new URL(fServer + query);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestProperty("User-Agent", fAgent);

        connection.setReadTimeout(fTimeout);
        connection.setDoInput(true);

        if (postData != null)
        {
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "text/xml");
            connection.setDoOutput(true);
            StreamResult result = new StreamResult(connection.getOutputStream());
            DOMSource source = new DOMSource(postData);
            fTransformer.transform(source, result);
        }
        else
        {
            connection.setRequestMethod("GET");
        }

        // Get the response
        connection.connect();
        InputStream responseStream = connection.getInputStream();
        Document response = fDocBuilder.parse(responseStream);

        if(fVerbose)
        {
            System.out.println("Response:");
            StreamResult result = new StreamResult(System.out);
            DOMSource source = new DOMSource(response);
            fTransformer.transform(source, result);
            System.out.println();
            System.out.println();
        }

        return response;
    }
    
 // Get message if the DLAP call was successful
    public String getSuccessMessage(Document result)
    {
    	String message = "";
        try
        {
            Element response = (Element) result.getFirstChild();
            if(response.getTagName().equals("response") && response.getAttribute("code").equals("OK"))
            {
                // Check any inner response elements
            	
            	DOMSource domSource = new DOMSource(result);
                StringWriter writer = new StringWriter();
                StreamResult strResult = new StreamResult(writer);
                TransformerFactory tf = TransformerFactory.newInstance();
                Transformer transformer = tf.newTransformer();
                transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.transform(domSource, strResult);               
                return writer.toString();                              
            }
        }
        catch (NullPointerException e) {}
        catch(TransformerException ex) {}
        return message;
    }
    
    // Checks if the DLAP call was successful
    public static Boolean IsSuccess(Document result)
    {
        try
        {
            Element response = (Element) result.getFirstChild();
            if(response.getTagName().equals("response") && response.getAttribute("code").equals("OK"))
            {
                // Check any inner response elements
                NodeList elements = result.getElementsByTagName("response");
                for(int i=0; i<elements.getLength(); i++)
                {
                    Element element = (Element)elements.item(i);
                    if(!element.getAttribute("code").equals("OK"))
                    {
                        return false;
                    }
                }
                return true;
            }
        }
        catch (NullPointerException e) {}
        return false;
    }

    // Returns the error message for a failed DLAP call
    public static String GetMessage(Document result)
    {
        try
        {
            // Find the first response with a code not equal to OK and return the message
            NodeList elements = result.getElementsByTagName("response");
            for(int i=0; i<elements.getLength(); i++)
            {
                Element element = (Element)elements.item(i);
                if(!element.getAttribute("code").equals("OK"))
                {
                    return element.getAttribute("message");
                }
            }
        }
        catch (NullPointerException e) {}
        return "Unknown error";
    }
    
    public String generateJsonFromXml(Document result, Models model) {
    	String xml = getSuccessMessage(result);
        JSON json = new XMLSerializer().read(xml);
        JSONObject jsonObj = JSONObject.fromObject(json.toString().replace("@", ""));        
        getModifiedXML(jsonObj, model);
        String formatedJson = jsonObj.toString().substring(jsonObj.toString().indexOf(":") + 1, jsonObj.toString().length()-1);
        System.out.println("JSON data : " + formatedJson);
        
        return formatedJson;
    }
    
    public void getModifiedXML(JSONObject jsonObj, Models model) {
    	jsonObj.remove("code");
        
        JSONArray jsonArr = jsonObj.getJSONArray(model.getBaseName());
        for (int i = 0, len = jsonArr.size(); i < len; i++) {
            JSONObject obj = jsonArr.getJSONObject(i);
            // Do your removals
            for (String element : model.getOmittedJsonList().split(",")) {
            	obj.remove(element);            	
			}
            obj.put("", "<img class=\"delete\" id=\"" + obj.getString("id") + "\" src=\"css/images/delete_button.png\" >");
        }
    }
    
	public String getfServer() {
		return fServer;
	}

	public String getfAgent() {
		return fAgent;
	}

	public int getfTimeout() {
		return fTimeout;
	}

	public Boolean getfVerbose() {
		return fVerbose;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getJsonOmittedList() {
		return jsonOmittedList;
	}

	public void setJsonOmittedList(String jsonOmittedList) {
		this.jsonOmittedList = jsonOmittedList;
	}
	
	
}