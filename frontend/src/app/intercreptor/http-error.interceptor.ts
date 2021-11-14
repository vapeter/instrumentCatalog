import { Injectable } from '@angular/core';

import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { NotificationService } from '../services/notification.service';
import {selectedLanguage} from '../app.component';

@Injectable()
export class HttpErrorInterceptor implements HttpInterceptor {

  constructor(private alertService: NotificationService) { }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
      request = request.clone({headers: request.headers.set('Accept-Language', selectedLanguage)});
      return next.handle(request).pipe(
      catchError((response: HttpErrorResponse) => {
          this.alertService.warn(`${response.error.message}`);
          return throwError(response.error);
      })
    );
  }
}
