import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnswersSummaryComponent } from './answers-summary.component';

describe('AnswersSummaryComponent', () => {
  let component: AnswersSummaryComponent;
  let fixture: ComponentFixture<AnswersSummaryComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AnswersSummaryComponent]
    });
    fixture = TestBed.createComponent(AnswersSummaryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
