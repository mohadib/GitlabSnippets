package org.openactive.gitlab.snippet;

import org.apache.http.HttpEntity;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;

import java.io.InputStream;

public class Test
{
    public static void main(String[] args)
    {
        CloseableHttpResponse resp = null;


        HttpEntity entity = MultipartEntityBuilder.create()
                .addTextBody("title", "Hello Java")
                .addBinaryBody("file", "{'yay':'json}".getBytes(), ContentType.APPLICATION_JSON, "foo.json")
                .build();

        HttpPost post = new HttpPost( "https://api.bitbucket.org/2.0/snippets/myTeam" );
        post.setEntity( entity );


        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("user", "pass"));


        try ( CloseableHttpClient client = HttpClientBuilder.create().setDefaultCredentialsProvider(credentialsProvider).build() )
        {
            resp = client.execute( post );
            byte[] buff = new byte[1024];
            int read = 0;
            InputStream is = resp.getEntity().getContent();
            StringBuilder builder = new StringBuilder();
            while ( (read = is.read( buff )) != -1 )
            {
                builder.append( new String( buff, 0, read ) );
            }
            JSONObject obj = new JSONObject( builder.toString() );
            String url = obj.getJSONObject("links").getJSONObject("html").getString("href");
            System.out.println(url);
        }
        catch ( Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try { resp.close(); } catch ( Exception e ) {}
        }
    }
}
