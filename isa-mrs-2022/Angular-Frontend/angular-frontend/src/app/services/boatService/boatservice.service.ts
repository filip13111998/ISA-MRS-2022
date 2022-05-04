import { BoatProfileDTO } from './../../models/response/http-boat-response/boat-profile';
import { BoatDTO } from './../../models/response/http-boat-response/boat-dto';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BoatserviceService {

  // public apiUrl: string = "http://localhost:8080/cottage?pageNum=0&pageSize=9";
  public apiUrl: string = "http://localhost:8080/home/boat";

  headers = new HttpHeaders().set('Content-Type', 'application/json');

  constructor(private http: HttpClient) { }

  public getOneBoat(id: number): Observable<BoatProfileDTO> {
    return this.http.get<BoatProfileDTO>(`${this.apiUrl}/${id}`, { headers: this.headers });
  }

  public getAllBoats(pageNum: number): Observable<BoatDTO[]> {
    return this.http.get<BoatDTO[]>(`${this.apiUrl}?pageNum=${pageNum}&pageSize=9`, { headers: this.headers });
  }

  public filterBoat(pageNum: number, sortType: String, sortDirection: Boolean, bs: any): Observable<BoatDTO[]> {
    return this.http.post<BoatDTO[]>(`${this.apiUrl}/sort/${sortType}/${sortDirection}/?pageNum=${pageNum}&pageSize=9`, bs, { headers: this.headers });
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
