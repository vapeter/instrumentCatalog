import { Injectable } from '@angular/core';
import {HttpClient, HttpEvent, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import {environment} from '../../environments/environment';

const apiUrl = environment.backendUrl + 'admin/upload';

@Injectable({
    providedIn: 'root'
  })
export class FileUploadService {

    constructor(
        private http: HttpClient) { }

    fileUpload(file: File): Observable<any> {

        const uploadData = new FormData();
        uploadData.append('file', file, file.name);
        return this.http.post(apiUrl, uploadData);
    }

}
