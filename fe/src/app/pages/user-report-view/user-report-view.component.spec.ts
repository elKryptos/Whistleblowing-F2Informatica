import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserReportViewComponent } from './user-report-view.component';

describe('UserReportViewComponent', () => {
  let component: UserReportViewComponent;
  let fixture: ComponentFixture<UserReportViewComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UserReportViewComponent]
    });
    fixture = TestBed.createComponent(UserReportViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
