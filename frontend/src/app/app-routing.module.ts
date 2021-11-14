import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { FileUploadComponent } from './components/file-upload/file-upload.component';
import {InstrumentsComponent} from './components/instruments/instruments.component';
import {StudentsComponent} from './components/students/students.component';
import {TeachersComponent} from './components/teachers/teachers.component';
import { AppAuthGuard } from './app-auth.guard';
import {WelcomeComponent} from './components/welcome/welcome.component';


const routes: Routes = [
  { path: 'instruments', component: InstrumentsComponent, canActivate: [AppAuthGuard]},
  { path: 'students', component: StudentsComponent, canActivate: [AppAuthGuard]},
  { path: 'welcome', component: WelcomeComponent},
  { path: 'teachers', component: TeachersComponent, canActivate: [AppAuthGuard]},
  { path: 'fileUpload', component: FileUploadComponent, canActivate: [AppAuthGuard]},
  { path: '', redirectTo: '/welcome', pathMatch: 'full'},
  { path: '**', redirectTo: '/' }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
