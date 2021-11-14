import { Injectable } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import {HttpClient, HttpEvent, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from '../../environments/environment';

const headers = new HttpHeaders().set('Content-Type', 'application/json');
const apiUrl = environment.backendUrl + 'admin/importFromFile';

@Injectable({
    providedIn: 'root'
  })
export class ImportFromFileService {

    constructor(
      private http: HttpClient) { }

    importForm: FormGroup = new FormGroup({
        importType: new FormControl('')
    });

    initializeFormGroup(): void {
        this.importForm.setValue({
            importType: 'student'
        });
    }

    importFromFile(fileName: string, importType: string): Observable<any> {
        return this.http.post<any>(apiUrl + '/' + fileName + '/' + importType, {headers});
    }



}
