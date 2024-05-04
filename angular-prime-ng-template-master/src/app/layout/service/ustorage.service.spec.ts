import { TestBed } from '@angular/core/testing';

import { UstorageService } from './ustorage.service';

describe('UstorageService', () => {
  let service: UstorageService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UstorageService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
