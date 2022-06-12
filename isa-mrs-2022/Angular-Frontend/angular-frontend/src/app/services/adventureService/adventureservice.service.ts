import { AdventurePricelistDTO } from './../../models/response/http-adventure-response/adventure-pricelist';
import { AdventureProfileDTO } from './../../models/response/http-adventure-response/adventure-profile';
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

  public getOneAdventure(id: number): Observable<AdventureProfileDTO> {
    return this.http.get<AdventureProfileDTO>(`${this.apiUrl}/${id}`, { headers: this.headers });
  }

  public getAllAdventures(pageNum: number): Observable<AdventureDTO[]> {
    return this.http.get<AdventureDTO[]>(`${this.apiUrl}?pageNum=${pageNum}&pageSize=9`, { headers: this.headers });
  }

  public filterAdventure(pageNum: number, sortType: String, sortDirection: Boolean, as: any): Observable<AdventureDTO[]> {
    return this.http.post<AdventureDTO[]>(`${this.apiUrl}/sort/${sortType}/${sortDirection}/?pageNum=${pageNum}&pageSize=9`, as, { headers: this.headers });
  }

  public getPricelist(id: number): Observable<AdventurePricelistDTO[]> {
    return this.http.get<AdventurePricelistDTO[]>(`${this.apiUrl}/pricelist/${id}`, { headers: this.headers });
  }
  public addMark(cs: any): Observable<Boolean> {
    return this.http.post<Boolean>(`${this.apiUrl}/mark`, cs, { headers: this.headers });
  }
  public addComplaint(cs: any): Observable<Boolean> {
    return this.http.post<Boolean>(`${this.apiUrl}/complaint`, cs, { headers: this.headers });
  }

  public delete(id: number): Observable<Boolean> {
    return this.http.get<Boolean>(`${this.apiUrl}/delete/${id}`, { headers: this.headers });
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
