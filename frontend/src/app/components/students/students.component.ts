import {Component, OnInit, ViewChild} from '@angular/core';
import {StudentService} from '../../services/student.service';
import {MatRow, MatTableDataSource} from '@angular/material/table';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {Student} from '../../models/student';
import {ThemePalette} from '@angular/material/core';
import {ProgressBarMode} from '@angular/material/progress-bar';
import {delay} from 'rxjs/operators';
import {NotificationService} from '../../services/notification.service';
import {TranslateService} from '@ngx-translate/core';


@Component({
  selector: 'app-students',
  templateUrl: './students.component.html',
  styleUrls: ['./students.component.css']
})
export class StudentsComponent implements OnInit {

  color: ThemePalette = 'primary';
  mode: ProgressBarMode = 'query';
  value = 50;
  bufferValue = 75;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  displayedColumns: string[] = ['educationalId', 'name', 'birthName', 'mothersName',
    'birthPlace', 'birthDate', 'gender',
    'actions',
  ];

  dataSource = new MatTableDataSource();

  constructor(
    private studentService: StudentService,
    private notificationService: NotificationService,
    private translateService: TranslateService
  ) { }

  ngOnInit(): void {
    this.renderDataTable();
  }

  renderDataTable(): void {
    this.studentService.getAllStudents().subscribe(result => {
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
    if (confirm(this.translateService.instant('teachers.quitConfirm'))) {
      this.studentService.quit(row as Student).pipe(delay(2000)).subscribe(result => {
        this.renderDataTable();
        this.notificationService.success(result.message);
      });
    }
  }
}
