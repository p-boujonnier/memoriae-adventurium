export interface MagazineListItem {
  id: number;
  issue: number;
  cover_url: string | null;
}


export interface MagazineDetail {
  id: number;
  issue: number;
  source: string;
  magazine: string;
  total_pages: number;
  language: string;
  year: number;
  pdf_url: string | null;
  cover_url: string | null;
}
