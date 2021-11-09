package com.example.seoulwalk.Activity;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class EXAM extends AsyncTask<String, Void, Document> {

    Context mContext;

    public EXAM(Context context){
        this.mContext = context;
    }
    @Override
    protected Document doInBackground(String... strings) {
        URL url;
        Document doc = null;
        try{
            url = new URL(strings[0]);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            doc = db.parse(new InputSource(url.openStream()));
            doc.getDocumentElement().normalize();
        }catch (Exception e){
            Toast.makeText(mContext, e.toString(), Toast.LENGTH_SHORT).show();
        }
        return doc;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Document doc) {
        super.onPostExecute(doc);
        NodeList itemNodeList = doc.getElementsByTagName("item");

        for(int i=0; i<itemNodeList.getLength(); i++){
            Node node = itemNodeList.item(i);
            Element element = (Element) node;

            NodeList titleNodeList = element.getElementsByTagName("title");
            String title = titleNodeList.item(0).getChildNodes().item(0).getNodeValue();
            Log.e("title", title);
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
