package br.com.alura.challenge.back.feature;

import static org.junit.Assert.assertEquals;

import org.springframework.http.ResponseEntity;

import br.com.alura.challenge.back.domain.dto.request.VideoRequest;
import br.com.alura.challenge.back.domain.dto.request.VideoUpdate;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;

public class VideoTest extends AbstractSteps {

    String categoryId;

    String videoId;

    String title;

    String description;

    String url;

    @Quando("realizar uma consulta de todos os videos e informo o {string} e a {string}")
    public void realizar_uma_consulta_de_todos_os_videos_e_informo_o_e_a(String string, String string2)
            throws Exception {

        ResponseEntity<String> videos = findAllVideoResponse(string, string2);

        this.testContext().setResponse(videos);
        this.testContext().set(HTTP_CODE_RESPONSE, videos.getStatusCode().value());

    }

    @Dado("que estou no endpoint para listar por categoria {string}")
    public void que_estou_no_endpoint_para_listar_por_categoria(String string) {
        this.categoryId = string;
    }

    @Quando("realizar uma consulta para listar videos por categoria")
    public void realizar_uma_consulta_para_listar_videos_por_categoria() throws Exception {

        ResponseEntity<String> videos = findAllVideoCategory(categoryId);

        this.testContext().setResponse(videos);
        this.testContext().set(HTTP_CODE_RESPONSE, videos.getStatusCode().value());
    }

    @Dado("que estou no endpoint para procurar por id {string}")
    public void que_estou_no_endpoint_para_procurar_por_id(String string) {
        this.videoId = string;
    }

    @Quando("realizar uma consulta por id")
    public void realizar_uma_consulta_por_id() throws Exception {

        ResponseEntity<String> videos = findByVideoId(videoId);

        this.testContext().setResponse(videos);
        this.testContext().set(HTTP_CODE_RESPONSE, videos.getStatusCode().value());
    }

    @Dado("que estou no endpoint para criar o video {string} , {string} , {string} , {string}")
    public void que_estou_no_endpoint_para_criar_o_video(String string, String string2, String string3,
            String string4) {
        this.title = string;
        this.description = string2;
        this.categoryId = string3;
        this.url = string4;
    }

    @Quando("realizar um registro")
    public void realizar_um_registro() throws Exception {

        VideoRequest videoRequest = VideoRequest.builder().title(title).description(description)
                .categoryId(Long.valueOf(categoryId)).url(url).build();

        ResponseEntity<String> video = create(videoRequest);

        this.testContext().setResponse(video);
        this.testContext().set(HTTP_CODE_RESPONSE, video.getStatusCode().value());
    }

    @Dado("que estou no endpoint para atualizar o video {string},{string} , {string} , {string}")
    public void que_estou_no_endpoint_para_atualizar_o_video(String string, String string2, String string3,
            String string4) {
        this.videoId = string;
        this.description = string2;
        this.categoryId = string3;
        this.url = string4;
    }

    @Quando("realizar uma atualizacao")
    public void realizar_uma_atualizacao() throws Exception {

        VideoUpdate videoUpdate = new VideoUpdate(description, url);

        ResponseEntity<String> video = update(videoUpdate, videoId);

        this.testContext().setResponse(video);
        this.testContext().set(HTTP_CODE_RESPONSE, video.getStatusCode().value());
    }

    @Dado("que estou no endpoint para deletar por id {string}")
    public void que_estou_no_endpoint_para_deletar_por_id(String string) {
        this.videoId = string;
    }

    @Quando("realizar uma delacao por id")
    public void realizar_uma_delacao_por_id() throws Exception {
        
        ResponseEntity<Void> video = delete(videoId);

        this.testContext().setResponse(video);
        this.testContext().set(HTTP_CODE_RESPONSE, video.getStatusCode().value());
    }

    @Entao("a API deve me retornar o código da operação {string} e os dados apresentados no corpo da solicitação")
    public void a_API_deve_me_retornar_o_código_da_operação_e_os_dados_apresentados_no_corpo_da_solicitação(
            String string) {
        assertEquals(Integer.parseInt(string), this.testContext().getResponse().getStatusCodeValue());
    }

}
