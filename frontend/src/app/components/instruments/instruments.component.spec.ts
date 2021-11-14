import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InstrumentsComponent } from './instruments.component';

describe('HangszerekComponent', () => {
  let component: InstrumentsComponent;
  let fixture: ComponentFixture<InstrumentsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InstrumentsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InstrumentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
