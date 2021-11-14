import {Component, Input, OnChanges, OnInit, SimpleChanges, ViewChild, ViewEncapsulation} from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatRow, MatTableDataSource} from '@angular/material/table';
import {InstrumentsService} from '../../services/instruments.service';
import {MatDialog, MatDialogConfig} from '@angular/material/dialog';
import {InstrumentComponent} from './instrument/instrument.component';
import {delay} from 'rxjs/operators';
import {NotificationService} from '../../services/notification.service';
import {MAT_DATE_FORMATS, ThemePalette} from '@angular/material/core';
import {ProgressBarMode} from '@angular/material/progress-bar';
import {InstrumentRentalService} from '../../services/instrument-rental.service';
import {RentComponent, MY_DATE_FORMATS} from './rent/rent.component';
import {TranslateService} from '@ngx-translate/core';

@Component({
  selector: 'app-instruments',
  templateUrl: './instruments.component.html',
  styleUrls: ['./instruments.component.css'],
  providers: [
    {provide: MAT_DATE_FORMATS, useValue: MY_DATE_FORMATS}
  ],
  encapsulation: ViewEncapsulation.None
})
export class InstrumentsComponent implements OnInit{

  color: ThemePalette = 'primary';
  mode: ProgressBarMode = 'query';
  value = 50;
  bufferValue = 75;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  displayedColumns: string[] = [
    'catalogNumber',
    'brand', 'model', 'instrumentType',
    'serialNumber', 'location', 'comment', 'rented', 'lastShowingDate',
    'highValue', 'waste', 'actions'
  ];

  dataSource = new MatTableDataSource();

  constructor(
    private instrumentsService: InstrumentsService,
    public dialog: MatDialog,
    private notificationService: NotificationService,
    private instrumentRentalService: InstrumentRentalService,
    private translate: TranslateService
  ) { }

  ngOnInit(): void {
    this.renderDataTable();
  }

  public renderDataTable(): void {
    this.instrumentsService.getAllInstruments().subscribe(result => {
      this.dataSource.data = result;
      this.dataSource.sort = this.sort;
      this.dataSource.paginator = this.paginator;
    });
  }

  applyFilter(event: Event): void {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  openDialog(): void {
    this.instrumentsService.initializeFormGroup();
    const dialogConfig = new MatDialogConfig();
    dialogConfig.width = '850px';

    const dialogRef = this.dialog.open(InstrumentComponent, dialogConfig);

    dialogRef.afterClosed()
      .pipe(delay(1500))
      .subscribe(result => {
        this.renderDataTable();
    });

  }

  onEdit(row: MatRow): void {
    console.log(row);
    this.instrumentsService.instrumentUpdateForm(row);
    const dialogRef = this.dialog.open(InstrumentComponent, {width: '850px'});

    dialogRef.afterClosed()
      .pipe(delay(1500))
      .subscribe(result => {
        this.renderDataTable();
      });
  }

  onDelete(id: string): void {
    if (confirm(this.translate.instant('instruments.deleteConfirm'))) {
      console.log(id);
      this.instrumentsService.deleteInstrumentById(id).pipe(delay(2000)).subscribe(result => {
        this.renderDataTable();
        this.notificationService.success(result.message);
      });
    }
  }

  rent(row: MatRow): void {
    this.instrumentRentalService.initializeFormGroup(row);

    const dialogRef = this.dialog.open(RentComponent, {width: '800px', minHeight: '600px'});

    dialogRef.afterClosed()
      .pipe(delay(1500))
      .subscribe(result => {
        this.renderDataTable();
      });
  }

  backToStorage(id: string): void {
    this.instrumentRentalService.backToStorage(id).subscribe(result => {
      this.renderDataTable();
      this.notificationService.success(result.message);
    });
  }

  showing(id: string): void {
    this.instrumentsService.instrumentShowing(id).subscribe(result => {
      this.renderDataTable();
      this.notificationService.success(result.message);
    });
  }
}
