import {Component, OnInit, ViewChild} from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatRow, MatTableDataSource} from '@angular/material/table';
import {TeacherService} from '../../services/teacher.service';
import {NotificationService} from '../../services/notification.service';
import {delay} from 'rxjs/operators';
import {Teacher} from '../../models/teacher';
import {ThemePalette} from '@angular/material/core';
import {ProgressBarMode} from '@angular/material/progress-bar';
import {TranslateService} from '@ngx-translate/core';
import {error} from 'protractor';

@Component({
  selector: 'app-teachers',
  templateUrl: './teachers.component.html',
  styleUrls: ['./teachers.component.css']
})
export class TeachersComponent implements OnInit {

  color: ThemePalette = 'primary';
  mode: ProgressBarMode = 'query';
  value = 50;
  bufferValue = 75;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  displayedColumns: string[] = ['educationalId', 'name', 'actions'];

  dataSource = new MatTableDataSource();

  constructor(
    private teacherService: TeacherService,
    private notificationService: NotificationService,
    private translate: TranslateService
  ) { }

  ngOnInit(): void {
    this.renderDataTable();
  }

  renderDataTable(): void {
    this.teacherService.getAllTeachers().subscribe(result => {
      this.dataSource.data = result;
      this.dataSource.sort = this.sort;
      this.dataSource.paginator = this.paginator;
    });
  }

  applyFilter(event: Event): void {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  quit(row: MatRow): void {
    if (confirm(this.translate.instant('teachers.quitConfirm'))) {
      this.teacherService.quit(row as Teacher).pipe(delay(2000)).subscribe(result => {
        this.renderDataTable();
        this.notificationService.success(result.message);
      });
    }
  }
}
