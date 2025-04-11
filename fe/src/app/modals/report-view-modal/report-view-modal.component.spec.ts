import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReportViewModalComponent } from './report-view-modal.component';

describe('ReportViewModalComponent', () => {
  let component: ReportViewModalComponent;
  let fixture: ComponentFixture<ReportViewModalComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ReportViewModalComponent]
    });
    fixture = TestBed.createComponent(ReportViewModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
