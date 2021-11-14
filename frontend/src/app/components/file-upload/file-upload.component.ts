import { Component, OnInit } from '@angular/core';
import { FileUploadService } from '../../services/fileUpload.service';
import { NotificationService } from '../../services/notification.service';
import { ImportFromFileService } from '../../services/importFromFile.service';
import {FileDownloadService} from '../../services/fileDownloadService';
import { saveAs } from 'file-saver';

const MIME_TYPES = {
  xlsx: 'application/vnd.openxmlformats-officedocument.spreadsheetxml.sheet'
};

@Component({
  selector: 'app-file-upload',
  templateUrl: './file-upload.component.html',
  styleUrls: ['./file-upload.component.css']
})
export class FileUploadComponent implements OnInit {

  selectedFile: File;
  uploadedFileName = '';
  uploaded = false;
  workingOnIt = false;

  constructor(
    private fileUploadService: FileUploadService,
    private notificationService: NotificationService,
    public importService: ImportFromFileService,
    private fileDownloadService: FileDownloadService
  ) { }

  ngOnInit(): void {
    this.uploaded = false;
    this.importService.initializeFormGroup();
  }

  onFileChanged(event): void {
    this.selectedFile = event.target.files[0];
    this.uploadedFileName = this.selectedFile.name;
  }

  onUpload(): void {
    this.fileUploadService.fileUpload(this.selectedFile).subscribe(result => {
      this.notificationService.success(result.message);
      this.uploaded = true;
    });
  }

  onImport(): void {
    this.workingOnIt = true;
    this.importService.importFromFile(this.uploadedFileName,
                                      this.importService.importForm.value.importType)
      .subscribe( result => {
        this.workingOnIt = false;
        this.notificationService.success(result.message);
      }, error => {
         this.workingOnIt = false;
      });
  }

  downloadFile(fileName): void {
    const EXT = fileName.substr(fileName.lastIndexOf('.') + 1);
    this.fileDownloadService.fileDownload({ 'fileName': fileName})
      .subscribe(data => {
        saveAs(new Blob([data], {type: MIME_TYPES[EXT]}), fileName);
      });
  }

}
