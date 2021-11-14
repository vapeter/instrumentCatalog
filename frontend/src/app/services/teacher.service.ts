import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Teacher} from '../models/teacher';
import {Injectable} from '@angular/core';
import {environment} from '../../environments/environment';

const headers = new HttpHeaders().set('Content-Type', 'application/json');
const apiUrl = environment.backendUrl + 'admin/teachers';

@Injectable({
  providedIn: 'root'
})
export class TeacherService {

  constructor(
    private http: HttpClient) { }

  getAllTeachers(): Observable<Teacher[]> {
    return this.http.get<Teacher[]>(apiUrl, {headers});
  }

  quit(teacher: Teacher): Observable<any> {
    return this.http.post(apiUrl + '/quit/', teacher, {headers});
  }
}
