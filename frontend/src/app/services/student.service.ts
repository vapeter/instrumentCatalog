import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Student} from '../models/student';
import {environment} from '../../environments/environment';

const headers = new HttpHeaders().set('Content-Type', 'application/json');
const apiUrl = environment.backendUrl + 'admin/students';

@Injectable({
  providedIn: 'root'
})
export class StudentService {

  constructor(
    private http: HttpClient) {}

  getAllStudents(): Observable<Student[]> {
    return this.http.get<Student[]>(apiUrl, {headers});
  }

  quit(student: Student): Observable<any> {
    return this.http.post(apiUrl + '/quit/', student, {headers});
  }
}
