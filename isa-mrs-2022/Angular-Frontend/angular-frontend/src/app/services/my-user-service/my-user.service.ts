import { MyUserDTO } from './../../models/response/my-user/my-user';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MyUserService {

  public apiUrl: string = "http://localhost:8080/home/myuser";

  headers = new HttpHeaders().set('Content-Type', 'application/json');

  constructor(private http: HttpClient) { }


  public getMyUser(username: any): Observable<MyUserDTO> {
    return this.http.get<MyUserDTO>(`${this.apiUrl}/${username}`, { headers: this.headers });
  }
  public editMyUser(myUser: any): Observable<Boolean> {
    return this.http.post<Boolean>(`${this.apiUrl}`, myUser, { headers: this.headers });
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
