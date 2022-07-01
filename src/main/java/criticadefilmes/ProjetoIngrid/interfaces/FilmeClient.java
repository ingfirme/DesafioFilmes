package criticadefilmes.ProjetoIngrid.interfaces;

import criticadefilmes.ProjetoIngrid.http.requests.OmdbapiRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "https://www.omdbapi.com", name="viafilme")
public interface FilmeClient {
    @GetMapping()
    OmdbapiRequest buscarFilmePorTitulo(@RequestParam(value = "t") String titulo, @RequestParam(value = "apiKey") String apiKey);


}
