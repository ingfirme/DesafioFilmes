package criticadefilmes.ProjetoIngrid.http.requests;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OmdbapiRequest {
        private String Title;
        private String Year;
        private String Genre;
        private String Director;
        private String Writer;
        private String Actors;
        private String Plot;
}
