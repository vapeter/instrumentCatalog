import {FormControl, FormGroup} from '@angular/forms';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {InstrumentRentalModel} from '../models/instrumentRental.model';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {DatePipe} from '@angular/common';
import {Instrument} from '../models/instrument';
import {environment} from '../../environments/environment';

const headers = new HttpHeaders().set('Content-Type', 'application/json');
const apiUrl = environment.backendUrl + 'admin/rent';

@Injectable({
  providedIn: 'root'
})
export class InstrumentRentalService {

  instrumentRentalForm: FormGroup = new FormGroup({
    rentalId: new FormControl(''),
    instrumentName: new FormControl(''),
    startOfRental: new FormControl(),
    instrument: new FormControl(''),
    student: new FormControl(null),
    teacher: new FormControl(null),
  });

  initializeFormGroup(instrument): void {
    this.instrumentRentalForm.setValue({
      rentalId: '',
      instrumentName: instrument.brand + ' '
          + instrument.model + ' '
          + instrument.instrumentType
          + ', leltári száma: '
          + instrument.catalogNumber,
      startOfRental: new Date(),
      instrument: instrument,
      student: null,
      teacher: null,
    });
  }

  constructor(
    private datePipe: DatePipe,
    private http: HttpClient) { }

  instrumentRental(instrumentRentalModel: InstrumentRentalModel): Observable<any> {
    instrumentRentalModel.startOfRental = this.datePipe.transform(instrumentRentalModel.startOfRental, 'yyyy-MM-dd');
    console.log(instrumentRentalModel);
    return this.http.post(apiUrl, instrumentRentalModel, {headers});
  }

  backToStorage(id: string): Observable<any> {
    return this.http.post(apiUrl + '/backToStorage/' + id, {headers});
  }

}
