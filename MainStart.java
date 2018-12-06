import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainStart {
    public static void main(String [] args){
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.testnet1.iotw.fun/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        IOTWWebService service = retrofit.create(IOTWWebService.class);

        Call<IOTWResponse> call = service.getMinerNumber("my_token");

        final long f = System.currentTimeMillis();

        call.enqueue(new Callback<IOTWResponse>(){

            @Override
            public void onResponse(@NotNull Call<IOTWResponse> call,@NotNull Response<IOTWResponse> response) {
                IOTWResponse iotwResponse = response.body();
                System.out.println("onResponse");
                System.out.println(System.currentTimeMillis() - f);

                if(iotwResponse != null)
                    System.out.println(iotwResponse.getResponse());
                else
                    System.out.println("Response is null");

                try{
                    Thread.sleep(1000);
                    call.clone().enqueue(this);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(@NotNull Call<IOTWResponse> call,@NotNull Throwable t) {
                t.printStackTrace();
                try{
                    Thread.sleep(1000);
                    call.clone().enqueue(this);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });
        System.out.println("Program started");
        System.out.println(System.currentTimeMillis() - f);
    }
}
