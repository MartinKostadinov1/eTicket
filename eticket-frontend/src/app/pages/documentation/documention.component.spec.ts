import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DocumentionComponent } from './documention.component';

describe('DocumentionComponent', () => {
  let component: DocumentionComponent;
  let fixture: ComponentFixture<DocumentionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DocumentionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DocumentionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
