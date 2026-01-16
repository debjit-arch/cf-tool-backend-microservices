package com.company.gmail.compliance.client;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.naming.NamingException;
import javax.naming.directory.Attribute;    // Corrected
import javax.naming.directory.Attributes;   // Corrected
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

import org.springframework.stereotype.Component;

@Component
public class DnsClient {

    public List<String> getTxtRecords(String host) {
        try {
            Hashtable<String, String> env = new Hashtable<>();
            env.put("java.naming.factory.initial", "com.sun.jndi.dns.DnsContextFactory");

            DirContext ctx = new InitialDirContext(env);
            Attributes attrs = ctx.getAttributes(host, new String[]{"TXT"});
            Attribute txt = attrs.get("TXT");

            List<String> records = new ArrayList<>();
            if (txt != null) {
                for (int i = 0; i < txt.size(); i++) {
                    records.add(txt.get(i).toString());
                }
            }
            return records;

        } catch (NamingException e) {
            // 🔴 THIS IS KEY
            // No TXT records ≠ DNS failure
            return List.of();
        }
    }
}
