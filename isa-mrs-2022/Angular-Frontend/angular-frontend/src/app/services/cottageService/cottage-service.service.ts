import { CottageProfileDTO } from './../../models/response/http-cottage-response/cottage-profile';
import { CottageSearchDTO } from './../../models/sort-filter-cottage/CottageSearchDTO';
import { CottageDTO } from '../../models/response/http-cottage-response/cottage-dto';
import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
@Injectable({
  providedIn: 'root'
})
export class CottageServiceService {


  // public apiUrl: string = "http://localhost:8080/cottage?pageNum=0&pageSize=9";
  public apiUrl: string = "http://localhost:8080/home/cottage";

  headers = new HttpHeaders().set('Content-Type', 'application/json');

  constructor(private http: HttpClient) { }

  public getOneCotages(id: number): Observable<CottageProfileDTO> {
    return this.http.get<CottageProfileDTO>(`${this.apiUrl}/${id}`, { headers: this.headers });
  }

  public getAllCotages(pageNum: number): Observable<CottageDTO[]> {
    return this.http.get<CottageDTO[]>(`${this.apiUrl}?pageNum=${pageNum}&pageSize=9`, { headers: this.headers });
  }

  public filterCottage(pageNum: number, sortType: String, sortDirection: Boolean, cs: any): Observable<CottageDTO[]> {
    return this.http.post<CottageDTO[]>(`${this.apiUrl}/sortBest/${sortType}/${sortDirection}/?pageNum=${pageNum}&pageSize=9`, cs, { headers: this.headers });
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
