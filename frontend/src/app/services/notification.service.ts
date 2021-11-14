import {Injectable} from '@angular/core';
import { MatSnackBar, MatSnackBarConfig } from '@angular/material/snack-bar';


@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  constructor(public snackBar: MatSnackBar) { }

  config: MatSnackBarConfig = {
    duration: 6000,
    horizontalPosition: 'right',
    verticalPosition: 'bottom'
  };

  success(msg): void {
    this.config['panelClass'] = ['alert', 'alert-success'];
    this.snackBar.open(msg, '',this.config);
  }

  warn(msg): void {
    this.config['panelClass'] = ['alert', 'alert-danger'];
    this.snackBar.open(msg, '', this.config);
  }
}
