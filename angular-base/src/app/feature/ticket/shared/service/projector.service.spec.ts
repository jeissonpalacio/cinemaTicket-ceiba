import { TestBed } from '@angular/core/testing';

import { MovieProjectorService } from './movieprojector.service';

describe('ProjectorService', () => {
  let service: MovieProjectorService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MovieProjectorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
