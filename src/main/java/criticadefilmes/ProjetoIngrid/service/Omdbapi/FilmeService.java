package criticadefilmes.ProjetoIngrid.service.Omdbapi;

import criticadefilmes.ProjetoIngrid.interfaces.FilmeClient;
import criticadefilmes.ProjetoIngrid.http.requests.OmdbapiRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class FilmeService {

    @Value("${apikey}")
    private String key;
    private final FilmeClient filmeClient;

    public FilmeService(FilmeClient filmeCLient){
        this.filmeClient = filmeCLient;
    }
    public OmdbapiRequest buscarFilme(String titulo){

        OmdbapiRequest buscar = filmeClient.buscarFilmePorTitulo(titulo,key);

        return buscar;
    }
}
