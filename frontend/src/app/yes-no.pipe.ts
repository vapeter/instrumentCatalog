import { Pipe, PipeTransform } from '@angular/core';
import {TranslateService} from '@ngx-translate/core';
import {Observable} from 'rxjs';

@Pipe({
  name: 'yesNo'
})
export class YesNoPipe implements PipeTransform {

  yes: Observable<any>;
  no: Observable<any>;

  constructor(private translate: TranslateService) {
    this.yes = this.translate.instant('yesNo.yes');
    this.no = this.translate.instant('yesNo.no');
  }

  transform(value: any, ...args: any[]): any {
    return value ? this.yes : this.no;
  }

}
