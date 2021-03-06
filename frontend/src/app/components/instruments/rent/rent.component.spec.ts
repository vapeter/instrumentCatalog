import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RentComponent } from './rent.component';

describe('KolcsonzesComponent', () => {
  let component: RentComponent;
  let fixture: ComponentFixture<RentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
