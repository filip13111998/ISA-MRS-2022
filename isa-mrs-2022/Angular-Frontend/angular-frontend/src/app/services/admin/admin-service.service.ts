import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { AdminLoginDTO } from 'src/app/models/response/admin-login/adminLogin';
import { Token } from 'src/app/models/response/login/login-token';

@Injectable({
  providedIn: 'root'
})
export class AdminServiceService {

  //User
  public apiUrl: string = "http://localhost:8080/admin";



  headers = new HttpHeaders().set('Content-Type', 'application/json');

  constructor(private http: HttpClient) { }


  public check(username: String): Observable<Boolean> {
    return this.http.post<Boolean>(`${this.apiUrl}/acc_active/${username}`, { headers: this.headers });
  }

  public newPassword(dto: any): Observable<Boolean> {
    return this.http.post<Boolean>(`${this.apiUrl}/new_pass`, dto, { headers: this.headers });
  }


  error(error: HttpErrorResponse) {
    let errorMessage = '';
    if (error.error instanceof ErrorEvent) {
      errorMessage = error.error.message;
    } else {
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    console.log(errorMessage);
    return throwError(() => {
      return errorMessage;
    });
  }
}
