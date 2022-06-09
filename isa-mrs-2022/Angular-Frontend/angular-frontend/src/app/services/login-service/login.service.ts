import { Token } from './../../models/response/login/login-token';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { ValidationAccountTokenDTO } from 'src/app/models/register/IsValid';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  //User
  public apiUrl: string = "http://localhost:8080/auth";



  headers = new HttpHeaders().set('Content-Type', 'application/json');

  constructor(private http: HttpClient) { }


  public login(token: any): Observable<Token> {

    return this.http.post<Token>(`${this.apiUrl}/login`, token, { headers: this.headers });
  }

  public register(registerUser: any): Observable<Boolean> {

    return this.http.post<Boolean>(`${this.apiUrl}/register`, registerUser, { headers: this.headers });
  }

  public isValid(username: any): Observable<ValidationAccountTokenDTO> {

    return this.http.get<ValidationAccountTokenDTO>(`${this.apiUrl}/isValid/${username}`, { headers: this.headers });
  }

  public validate(username: any): Observable<Boolean> {

    return this.http.get<Boolean>(`${this.apiUrl}/validate/${username}`, { headers: this.headers });
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
