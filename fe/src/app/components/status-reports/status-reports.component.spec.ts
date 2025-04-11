import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StatusReportsComponent } from './status-reports.component';

describe('StatusReportsComponent', () => {
  let component: StatusReportsComponent;
  let fixture: ComponentFixture<StatusReportsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [StatusReportsComponent]
    });
    fixture = TestBed.createComponent(StatusReportsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
