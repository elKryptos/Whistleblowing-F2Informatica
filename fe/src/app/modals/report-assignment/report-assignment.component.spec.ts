import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReportAssignmentComponent } from './report-assignment.component';

describe('ReportAssignmentComponent', () => {
  let component: ReportAssignmentComponent;
  let fixture: ComponentFixture<ReportAssignmentComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ReportAssignmentComponent]
    });
    fixture = TestBed.createComponent(ReportAssignmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
