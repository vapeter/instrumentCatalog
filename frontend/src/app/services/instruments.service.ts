import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Instrument} from '../models/instrument';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {environment} from '../../environments/environment';
import {InstrumentRentalModel} from '../models/instrumentRental.model';

const headers = new HttpHeaders().set('Content-Type', 'application/json');
const apiUrl = environment.backendUrl + 'admin/instruments';

@Injectable({
  providedIn: 'root'
})
export class InstrumentsService {

  instrumentForm: FormGroup = new FormGroup({
    instrumentId: new FormControl(''),
    catalogNumber: new FormControl('', Validators.required),
    brand: new FormControl('', Validators.required),
    model: new FormControl('', Validators.required),
    instrumentType: new FormControl('', Validators.required),
    serialNumber: new FormControl(''),
    location: new FormControl(''),
    comment: new FormControl(''),
    highValue: new FormControl(false, Validators.required),
    waste: new FormControl(false, Validators.required),
    rented: new FormControl(false),
    lastShowingDate: new FormControl()
  });

  initializeFormGroup(): void {
    this.instrumentForm.setValue({
      instrumentId: '',
      catalogNumber: '',
      brand: '',
      model: '',
      instrumentType: '',
      serialNumber: '',
      location: '',
      comment: '',
      highValue: '',
      waste: '',
      rented: false,
      lastShowingDate: null
    });
  }

  instrumentUpdateForm(instrument): void {
    this.instrumentForm.setValue({
      instrumentId: instrument.instrumentId,
      catalogNumber: instrument.catalogNumber,
      brand: instrument.brand,
      model: instrument.model,
      instrumentType: instrument.instrumentType,
      serialNumber: instrument.serialNumber,
      location: instrument.location,
      comment: instrument.comment,
      highValue: instrument.highValue,
      waste: instrument.waste,
      rented: instrument.rented,
      lastShowingDate: instrument.lastShowingDate
    });
  }

  constructor(
    private http: HttpClient) { }

  getAllInstruments(): Observable<Instrument[]> {
    return this.http.get<Instrument[]>(apiUrl, {headers});
  }

  createInstrument(instrument: Instrument): Observable<any> {
    return this.http.post(apiUrl, instrument, {headers});
  }

  deleteInstrumentById(id: string): Observable<any> {
    return this.http.post(apiUrl + '/delete/' + id, {headers});
  }

  instrumentShowing(id: string): Observable<any> {
    return this.http.post(apiUrl + '/showing/' + id, {headers});
  }

  instrumentHistory(id: string): Observable<InstrumentRentalModel> {
    return this.http.post<InstrumentRentalModel>(apiUrl + '/historyOfInstrumentRentals' + id, {headers});
  }
}
