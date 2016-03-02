package com.tencent.mm.sdk.platformtools;

import com.tencent.mm.A;
import com.tencent.mm.BuildConfig;
import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map$Entry;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public final class r {
    private static boolean jfM;

    static {
        r.jfM = false;
        if(!BuildConfig.SKIP) {
            A.a();
        }
    }

    public static Map AC(String arg9) {
        HashMap v0;
        int v8 = 2;
        if(arg9 == null || arg9.length() <= 0) {
            Map v0_1 = null;
        }
        else {
            v0 = new HashMap();
            String[] v3 = arg9.split("\n");
            int v4 = v3.length;
            int v1;
            for(v1 = 0; v1 < v4; ++v1) {
                String v5 = v3[v1];
                if(v5 != null && v5.length() > 0) {
                    String[] v5_1 = v5.trim().split("=", v8);
                    if(v5_1 != null && v5_1.length >= v8) {
                        String v6 = v5_1[0];
                        v5 = v5_1[1];
                        if(v6 != null && v6.length() > 0 && (v6.matches("^[a-zA-Z0-9_.]*"))) {
                            ((Map)v0).put(v6, v5);
                        }
                    }
                }
            }

            if(!r.jfM) {
                goto label_6;
            }

            r.J(((Map)v0));
        }

        label_6:
        return ((Map)v0);
    }

    public static Map H(String msgBody, String msgTag, String encoding) {  // hookMap
        // seem to be the decoder of xml stream sent by Wechat server
        Document document;
        // XML document lib
        DocumentBuilder documentBuilder;
        Map map = null;
        int v1 = msgBody == null ? -1 : msgBody.indexOf(60);
        if(v1 < 0) {
            v.e("!32@/B4Tb64lLpL0kVyHeA8M6nJRdTdS8jEg", "text not in xml format");
            goto label_msgBody_is_null_empty;
        }

        if(v1 > 0) {
            v.w("!32@/B4Tb64lLpL0kVyHeA8M6nJRdTdS8jEg", "fix xml header from + %d", new Object[]{Integer
                    .valueOf(v1)});
            msgBody = msgBody.substring(v1);
        }

        if(msgBody == null) {
            goto label_msgBody_is_null_empty;
        }

        if(msgBody.length() <= 0) {
            goto label_msgBody_is_null_empty;
        }

        HashMap hashMap = new HashMap();
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            if(documentBuilder != null) {
                goto label_33;
            }
        }
        catch(ParserConfigurationException v1_2) {
            goto label_msgBody_is_null_empty;
        }

        v.e("!32@/B4Tb64lLpL0kVyHeA8M6nJRdTdS8jEg", "new Document Builder failed");
        goto label_msgBody_is_null_empty;
        try {
            label_33:
            InputSource inputSource = new InputSource(new ByteArrayInputStream(msgBody.getBytes()));
            if(encoding != null) {
                v3.setEncoding(encoding);
            }

            document = documentBuilder.parse(v3);
        }
        catch(DOMException e) {
            document = ((Document) map);
            goto label_42;
        }

        try {
            document.normalize();
        }
        catch(DOMException v3_1) {
        }

        label_42:
        if(document == null) {
            v.e("!32@/B4Tb64lLpL0kVyHeA8M6nJRdTdS8jEg", "new Document failed");
            goto label_msgBody_is_null_empty;
        }

        Element v2_4 = document.getDocumentElement();
        if(v2_4 == null) {
            v.e("!32@/B4Tb64lLpL0kVyHeA8M6nJRdTdS8jEg", "getDocumentElement failed");
            goto label_msgBody_is_null_empty;
        }

        if(msgTag.equals(v2_4.getNodeName())) {
            r.a(((Map)hashMap), "", "", ((Node)v2_4), 0);
        }
        else {
            NodeList v2_5 = v2_4.getElementsByTagName(msgTag);
            if(v2_5.getLength() <= 0) {
                v.e("!32@/B4Tb64lLpL0kVyHeA8M6nJRdTdS8jEg", "parse item null");
                goto label_msgBody_is_null_empty;
            }
            else {
                if(v2_5.getLength() > 1) {
                    v.w("!32@/B4Tb64lLpL0kVyHeA8M6nJRdTdS8jEg", "parse items more than one");
                }

                r.a(((Map)hashMap), "", "", v2_5.item(0), 0);
            }
        }

        if(r.jfM) {
            r.J(((Map)hashMap));
        }

        HashMap v0_1 = hashMap;
        label_9:
        return ((Map)v0_1);
    }

    private static void J(Map arg5) {
        if(arg5.size() <= 0) {
            v.v("!32@/B4Tb64lLpL0kVyHeA8M6nJRdTdS8jEg", "empty values");
        }
        else {
            Iterator v2 = arg5.entrySet().iterator();
            while(v2.hasNext()) {
                Object v0 = v2.next();
                v.v("!32@/B4Tb64lLpL0kVyHeA8M6nJRdTdS8jEg", "key=" + ((Map$Entry)v0).getKey() + " value="
                        + ((Map$Entry)v0).getValue());
            }
        }
    }

    private static void a(Map arg8, String arg9, String arg10, Node arg11, int arg12) {
        String v2;
        int v3 = 0;
        if(arg11.getNodeName().equals("#text")) {
            arg8.put(arg9, arg11.getNodeValue());
            arg8.put(arg10, arg11.getNodeValue());
        }
        else if(arg11.getNodeName().equals("#cdata-section")) {
            arg8.put(arg9, arg11.getNodeValue());
            arg8.put(arg10, arg11.getNodeValue());
        }
        else {
            String v1 = arg9 + "." + arg11.getNodeName();
            String v0 = arg10 + "." + arg11.getNodeName();
            if(arg12 > 0) {
                v1 = v1 + arg12;
                arg8.put(v1, arg11.getNodeValue());
                v0 = v0 + "#" + arg12;
                arg8.put(v0, arg11.getNodeValue());
                v2 = v1;
                v1 = v0;
            }
            else {
                arg8.put(v1, arg11.getNodeValue());
                arg8.put(v0, arg11.getNodeValue());
                v2 = v1;
                v1 = v0;
            }

            NamedNodeMap v4 = arg11.getAttributes();
            if(v4 != null) {
                int v0_1;
                for(v0_1 = 0; v0_1 < v4.getLength(); ++v0_1) {
                    Node v5 = v4.item(v0_1);
                    arg8.put(v2 + ".$" + v5.getNodeName(), v5.getNodeValue());
                    arg8.put(v1 + ".$" + v5.getNodeName(), v5.getNodeValue());
                }
            }

            HashMap v4_1 = new HashMap();
            NodeList v5_1 = arg11.getChildNodes();
            while(v3 < v5_1.getLength()) {
                Node v6 = v5_1.item(v3);
                v0_1 = bc.d(v4_1.get(v6.getNodeName()));
                r.a(arg8, v2, v1, v6, v0_1);
                v4_1.put(v6.getNodeName(), Integer.valueOf(v0_1 + 1));
                ++v3;
            }
        }
    }
}

