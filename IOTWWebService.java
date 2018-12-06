import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

interface IOTWWebService {
    @GET("iotw_getMicroMinerNumber")
    Call<IOTWResponse> getMinerNumber(@Header("access-token") String token);
}
