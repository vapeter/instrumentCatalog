import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {environment} from '../../environments/environment';

const apiUrl = environment.backendUrl + 'admin/';

@Injectable({
  providedIn: 'root'
})
export class FileDownloadService {

  constructor(
    private http: HttpClient) {}

  fileDownload(data) {
    const REQUEST_PARAMS = new HttpParams().set('fileName', 'import_samples/' + data.fileName);
    const REQUEST_URI = apiUrl + 'download';
    return this.http.get(REQUEST_URI, {
      params: REQUEST_PARAMS,
      responseType: 'arraybuffer'
    });
  }
}
