mport { TestBed } from '@angular/core/testing';

import { EmailVerificationServiceService } from './email-verification-service.service';

describe('EmailVerificationServiceService', () => {
  let service: EmailVerificationServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EmailVerificationServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
