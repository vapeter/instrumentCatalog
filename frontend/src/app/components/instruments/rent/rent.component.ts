import {Component, Inject, OnInit} from '@angular/core';
import {InstrumentRentalService} from '../../../services/instrument-rental.service';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {InstrumentRentalModel} from '../../../models/instrumentRental.model';
import {DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE} from '@angular/material/core';
import {DatePipe} from '@angular/common';
import {TeacherService} from '../../../services/teacher.service';
import {Teacher} from '../../../models/teacher';
import {NotificationService} from '../../../services/notification.service';
import {StudentService} from '../../../services/student.service';
import {Student} from '../../../models/student';


export const MY_DATE_FORMATS = {
    parse: {
        dateInput: 'YYYY-MM-DD',
    },
    display: {
        dateInput: 'YYYY-MM-DD',
        monthYearLabel: 'YYYY',
        dateA11yLabel: 'LL',
        monthYearA11yLabel: 'YYYY'
    },
};

@Component({
    selector: 'app-rent',
    templateUrl: './rent.component.html',
    styleUrls: ['./rent.component.css'],
    providers: [
        {provide: MAT_DATE_FORMATS, useValue: MY_DATE_FORMATS}
    ]
})
export class RentComponent implements OnInit {

    instrumentRental: InstrumentRentalModel;
    maxDate = new Date();
    public personOfRental = 'student';
    teachers: Teacher[];
    students: Student[];


    constructor(
        private notificationService: NotificationService,
        private datePipe: DatePipe,
        public dialogRef: MatDialogRef<RentComponent>,
        public instrumentRentalService: InstrumentRentalService,
        private studentService: StudentService,
        private teacherService: TeacherService) {
    }

    ngOnInit(): void {
        this.teacherService.getAllTeachers().subscribe(result => {
            this.teachers = result;
        });
        this.studentService.getAllStudents().subscribe(result => {
            this.students = result;
        });
    }


    onNoClick(): void {
        this.instrumentRentalService.instrumentRentalForm.reset();
        this.dialogRef.close();
    }

    saveRental(): void {
        this.instrumentRental = this.instrumentRentalService.instrumentRentalForm.value;

        if (this.personOfRental === 'teacher') {
            this.instrumentRental.student = null;
        }
        if (this.personOfRental === 'student') {
            this.instrumentRental.teacher = null;
        }

        this.instrumentRentalService.instrumentRental(this.instrumentRental).subscribe(result => {
            this.notificationService.success(result.message);
        });

        this.dialogRef.close();
    }


}
