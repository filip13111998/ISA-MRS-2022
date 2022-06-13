import { ReportSearhDTO } from './../../models/report/report-search';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { AdminLoginDTO } from 'src/app/models/response/admin-login/adminLogin';
import { AdminProfileDTO } from 'src/app/models/response/admin/admin-profile';
import { ComplaintAdminDTO } from 'src/app/models/response/admin/complaint-admin';
import { ExtendComplaintDTO } from 'src/app/models/response/admin/extended-complaint';
import { ExtendMarkAdminDTO } from 'src/app/models/response/admin/extended-mark';
import { MarkAdminDTO } from 'src/app/models/response/admin/mark-admin';
import { OwnerEntityDTO } from 'src/app/models/response/admin/owner-entity';
import { UserDeleteDTO } from 'src/app/models/response/admin/user-delete';
import { Token } from 'src/app/models/response/login/login-token';
import { LoyalityDTO } from 'src/app/models/response/loyality/loyality';
import { ReportDTO } from 'src/app/models/report/report';

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

  /* LOYALITY PROGRAM */
  public loyality(): Observable<LoyalityDTO> {
    return this.http.get<LoyalityDTO>(`${this.apiUrl}/loyality`, { headers: this.headers });
  }

  public saveLoyality(l: LoyalityDTO): Observable<Boolean> {
    return this.http.post<Boolean>(`${this.apiUrl}/saveLoyality`, l, { headers: this.headers });
  }

  /* ADMIN UPDATE-ADD-GET API */
  public getOneAdmin(username: string): Observable<AdminProfileDTO> {
    return this.http.get<AdminProfileDTO>(`${this.apiUrl}/getOneAdmin/${username}`, { headers: this.headers });
  }

  public saveAdmin(a: AdminProfileDTO): Observable<Boolean> {
    return this.http.post<Boolean>(`${this.apiUrl}/saveAdmin`, a, { headers: this.headers });
  }


  public updateAdmin(a: AdminProfileDTO): Observable<Boolean> {
    return this.http.post<Boolean>(`${this.apiUrl}/updateAdmin`, a, { headers: this.headers });
  }

  /*MARK-COMPLAINT*/

  /*COTTAGE*/
  public acceptCottageMark(m: MarkAdminDTO): Observable<Boolean> {
    return this.http.post<Boolean>(`${this.apiUrl}/acceptCottageMark`, m, { headers: this.headers });
  }


  public declineCottageMark(m: MarkAdminDTO): Observable<Boolean> {
    return this.http.post<Boolean>(`${this.apiUrl}/declineCottageMark`, m, { headers: this.headers });
  }

  public getAllCottageMark(): Observable<ExtendMarkAdminDTO[]> {
    return this.http.get<ExtendMarkAdminDTO[]>(`${this.apiUrl}/getAllCottageMark`, { headers: this.headers });
  }

  public answerCottageComplaint(c: ComplaintAdminDTO): Observable<Boolean> {
    return this.http.post<Boolean>(`${this.apiUrl}/answerCottageComplaint`, c, { headers: this.headers });
  }

  public getAllCottageComplaint(): Observable<ExtendComplaintDTO[]> {
    return this.http.get<ExtendComplaintDTO[]>(`${this.apiUrl}/getAllCottageComplaint`, { headers: this.headers });
  }

  /*BOAT*/
  public accepBoatMark(m: MarkAdminDTO): Observable<Boolean> {
    return this.http.post<Boolean>(`${this.apiUrl}/accepBoatMark`, m, { headers: this.headers });
  }


  public declineBoatMark(m: MarkAdminDTO): Observable<Boolean> {
    return this.http.post<Boolean>(`${this.apiUrl}/declineBoatMark`, m, { headers: this.headers });
  }

  public getAllBoatMark(): Observable<ExtendMarkAdminDTO[]> {
    return this.http.get<ExtendMarkAdminDTO[]>(`${this.apiUrl}/getAllBoatMark`, { headers: this.headers });
  }

  public answerBoatComplaint(c: ComplaintAdminDTO): Observable<Boolean> {
    return this.http.post<Boolean>(`${this.apiUrl}/answerBoatComplaint`, c, { headers: this.headers });
  }

  public getAllBoatComplaint(): Observable<ExtendComplaintDTO[]> {
    return this.http.get<ExtendComplaintDTO[]>(`${this.apiUrl}/getAllBoatComplaint`, { headers: this.headers });
  }


  /*ADVENTURE*/
  public acceptAdventureMark(m: MarkAdminDTO): Observable<Boolean> {
    return this.http.post<Boolean>(`${this.apiUrl}/acceptAdventureMark`, m, { headers: this.headers });
  }


  public declineAdventureMark(m: MarkAdminDTO): Observable<Boolean> {
    return this.http.post<Boolean>(`${this.apiUrl}/declineAdventureMark`, m, { headers: this.headers });
  }

  public getAllAdventureMark(): Observable<ExtendMarkAdminDTO[]> {
    return this.http.get<ExtendMarkAdminDTO[]>(`${this.apiUrl}/getAllAdventureMark`, { headers: this.headers });
  }

  public answerAdventureComplaint(c: ComplaintAdminDTO): Observable<Boolean> {
    return this.http.post<Boolean>(`${this.apiUrl}/answerAdventureComplaint`, c, { headers: this.headers });
  }

  public getAllCAdventureComplaint(): Observable<ExtendComplaintDTO[]> {
    return this.http.get<ExtendComplaintDTO[]>(`${this.apiUrl}/getAllCAdventureComplaint`, { headers: this.headers });
  }

  /*DELETE USER*/

  public acceptDeleteUser(username: string): Observable<Boolean> {
    return this.http.get<Boolean>(`${this.apiUrl}/acceptDeleteUser/${username}`, { headers: this.headers });
  }

  public declineDeleteUser(username: string): Observable<Boolean> {
    return this.http.get<Boolean>(`${this.apiUrl}/declineDeleteUser/${username}`, { headers: this.headers });
  }

  public findAllDeletedUsers(): Observable<UserDeleteDTO[]> {
    return this.http.get<UserDeleteDTO[]>(`${this.apiUrl}/findAllDeletedUsers`, { headers: this.headers });
  }

  /*COTTAGE OWNER*/
  public deleteCottageOwner(id: number): Observable<Boolean> {
    return this.http.get<Boolean>(`${this.apiUrl}/deleteCottageOwner/${id}`, { headers: this.headers });
  }

  public findAllCottageOwner(): Observable<OwnerEntityDTO[]> {
    return this.http.get<OwnerEntityDTO[]>(`${this.apiUrl}/findAllCottageOwner`, { headers: this.headers });
  }

  /*BOAT OWNER*/
  public deleteBoatOwner(id: number): Observable<Boolean> {
    return this.http.get<Boolean>(`${this.apiUrl}/deleteBoatOwner/${id}`, { headers: this.headers });
  }

  public findAllBoatOwner(): Observable<OwnerEntityDTO[]> {
    return this.http.get<OwnerEntityDTO[]>(`${this.apiUrl}/findAllBoatOwner`, { headers: this.headers });
  }

  /*INSTRUCTOR*/
  public deleteInstructor(id: number): Observable<Boolean> {
    return this.http.get<Boolean>(`${this.apiUrl}/deleteInstructor/${id}`, { headers: this.headers });
  }

  public findAllInstructor(): Observable<OwnerEntityDTO[]> {
    return this.http.get<OwnerEntityDTO[]>(`${this.apiUrl}/findAllInstructor`, { headers: this.headers });
  }

  /*REPORT*/
  public report(rs: ReportSearhDTO): Observable<ReportDTO> {
    return this.http.post<ReportDTO>(`${this.apiUrl}/report`, rs, { headers: this.headers });
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
