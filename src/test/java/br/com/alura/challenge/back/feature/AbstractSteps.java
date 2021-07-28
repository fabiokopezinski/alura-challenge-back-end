package br.com.alura.challenge.back.feature;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import br.com.alura.challenge.back.domain.dto.request.VideoRequest;
import br.com.alura.challenge.back.domain.dto.request.VideoUpdate;
import br.com.alura.challenge.back.domain.dto.response.VideoResponse;

public class AbstractSteps {

    public static final String HTTP_CODE_RESPONSE = "httpCodeResponse";
    public static final String VIDEO_CODE = "videoCode";

    @Autowired
    protected TestRestTemplate template;

    @LocalServerPort
    private int port;

    @Value("${server.servlet.context-path:/}")
    private String servletPath;

    @Value("${spring.profiles.active:dev}")
    protected String profile;

    public String baseUrl() {
        return String.format("http://localhost:%d%s", port, servletPath);
    }

    public TestContext testContext() {
        return TestContext.CONTEXT;
    }

    protected ResponseEntity<String> findAllVideoResponse(String limit, String page) throws Exception {

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Page<VideoResponse>> body = new HttpEntity<Page<VideoResponse>>(headers);

        String url = String.format("%s/videos?page=%s&limit=%s", baseUrl(), page, limit);

        return template.exchange(url, HttpMethod.GET, body, String.class);

    }

    protected ResponseEntity<String> findAllVideoCategory(String categoriaId) throws Exception {

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Page<VideoResponse>> body = new HttpEntity<Page<VideoResponse>>(headers);

        String url = String.format("%s/videos/%s/categorias", baseUrl(), categoriaId);

        return template.exchange(url, HttpMethod.GET, body, String.class);

    }

    protected ResponseEntity<String> findByVideoId(String videoId) throws Exception {

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<VideoResponse> body = new HttpEntity<VideoResponse>(headers);

        String url = String.format("%s/videos/%s", baseUrl(), videoId);

        return template.exchange(url, HttpMethod.GET, body, String.class);
    }

    protected ResponseEntity<String> create(VideoRequest videoRequest) throws Exception {

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<VideoRequest> body = new HttpEntity<VideoRequest>(videoRequest, headers);

        String url = String.format("%s/videos", baseUrl());

        return template.exchange(url, HttpMethod.POST, body, String.class);
    }


    protected ResponseEntity<String> update(VideoUpdate videoUpdate,String videoId) throws Exception {
        
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);
       
        HttpEntity<VideoUpdate> body = new HttpEntity<VideoUpdate>(videoUpdate, headers);

        String url = String.format("%s/videos/%s", baseUrl(), videoId);

        return template.exchange(url, HttpMethod.PATCH,body, String.class);
    }

    protected ResponseEntity<Void> delete(String videoId) throws Exception {

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<VideoResponse> body = new HttpEntity<VideoResponse>(headers);

        String url = String.format("%s/videos/%s", baseUrl(), videoId);

        return template.exchange(url, HttpMethod.DELETE, body, Void.class);
    }

}
