REPORT ZBC400_00_GS.

DATA: wa_scarr TYPE scarr.

SELECT * FROM scarr
        INTO wa_scarr.
NEW-LINE.
WRITE wa_scarr-carrid.
WRITE wa_scarr-carrname.
WRITE wa_scarr-url.
ENDSELECT.