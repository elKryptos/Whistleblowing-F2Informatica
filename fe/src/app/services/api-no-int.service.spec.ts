import { TestBed } from '@angular/core/testing';

import { ApiNoIntService } from './api-no-int.service';

describe('ApiNoIntService', () => {
  let service: ApiNoIntService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ApiNoIntService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
