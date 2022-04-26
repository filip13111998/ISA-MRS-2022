import { AdventureDTO } from './../../models/response/http-adventure-response/adventure-dto';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AdventureserviceService {

  // public apiUrl: string = "http://localhost:8080/cottage?pageNum=0&pageSize=9";
  public apiUrl: string = "http://localhost:8080/home/adventure";

  headers = new HttpHeaders().set('Content-Type', 'application/json');

  constructor(private http: HttpClient) { }

  public getAllAdventures(pageNum: number): Observable<AdventureDTO[]> {
    return this.http.get<AdventureDTO[]>(`${this.apiUrl}?pageNum=${pageNum}&pageSize=9`, { headers: this.headers });
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
