import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {Instrument} from '../../../models/instrument';
import {InstrumentsService} from '../../../services/instruments.service';
import {NotificationService} from '../../../services/notification.service';


@Component({
  selector: 'app-instrument',
  templateUrl: './instrument.component.html',
  styleUrls: ['./instrument.component.css']
})
export class InstrumentComponent implements OnInit {

  instrument: Instrument;

  constructor( public dialogRef: MatDialogRef<InstrumentComponent>,
               public instrumentsService: InstrumentsService,
               @Inject(MAT_DIALOG_DATA) public data: Instrument,
               private notificationService: NotificationService) { }

  ngOnInit(): void {}

  onNoClick(): void {
    this.instrumentsService.instrumentForm.reset();
    this.dialogRef.close();
  }

  saveInstrument(): void {
    this.instrument = this.instrumentsService.instrumentForm.value;
    this.instrumentsService.createInstrument(this.instrument).
      subscribe(result => {
      this.notificationService.success(result.message);
    });

    this.dialogRef.close();
  }

}
