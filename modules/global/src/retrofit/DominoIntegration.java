package retrofit;

import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

/**
 *
 * Интерфейс описывает интеграцию с Domino
 * @author adms-Ahmetshin-RM
 *
 */
public interface DominoIntegration {
	@GET("cubauser1")
	Call<List<Employee>> getEmployeeList (@Header("Authorization") String aurhKey);
	
	@Headers("Content-Type: application/json")
	@POST("updateData")
	Call<Employee> getEmployeebyTab (@Header("Authorization") String aurhKey ,
	                                 @Body String tabNumber);
	
	@GET("cubauser")
	Call<List<Employee>> createEmployees (@Header("Authorization") String aurhKey);
	
	@GET("cubaorgunit")
	Call<List<EmplOrgUnit>> createOrgUnits (@Header("Authorization") String aurhKey);
}
