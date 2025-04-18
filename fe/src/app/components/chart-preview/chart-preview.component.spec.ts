import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChartPreviewComponent } from './chart-preview.component';

describe('ChartPreviewComponent', () => {
  let component: ChartPreviewComponent;
  let fixture: ComponentFixture<ChartPreviewComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ChartPreviewComponent]
    });
    fixture = TestBed.createComponent(ChartPreviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
