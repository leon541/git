package com.leon;

import java.io.Serializable;


public class SelectItem implements Serializable
{
    private static final long serialVersionUID = 8841094741464512226L;
    // FIELDS
    private Object _value;
    private String _label;
    private String _description;
    private boolean _disabled;
    private boolean _escape;

    // CONSTRUCTORS
    public SelectItem()
    {
    }

    public SelectItem(Object value)
    {
        _value = value;
        _label = value == null ? null : value.toString();
        _description = null;
        _disabled = false;
        _escape=true;
    }

    public SelectItem(Object value, String label)
    {
        _value = value;
        _label = label;
        _description = null;
        _disabled = false;
        _escape = true;
    }

    public SelectItem(Object value, String label, String description)
    {
        _value = value;
        _label = label;
        _description = description;
        _disabled = false;
        _escape = true;
    }

    public SelectItem(Object value, String label, String description, boolean disabled)
    {
        _value = value;
        _label = label;
        _description = description;
        _disabled = disabled;
        _escape = true;
    }

    public SelectItem(Object value, String label, String description, boolean disabled, boolean escape)
    {
        _value = value;
        _label = label;
        _description = description;
        _disabled = disabled;
        this._escape = escape;
    }
    
    // METHODS
    public String getDescription()
    {
        return _description;
    }

    public void setDescription(String description)
    {
        _description = description;
    }

    public boolean isDisabled()
    {
        return _disabled;
    }

    public void setDisabled(boolean disabled)
    {
        _disabled = disabled;
    }

    public String getLabel()
    {
        return _label;
    }

    public void setLabel(String label)
    {
        if (label == null)
          throw new NullPointerException("label");
        _label = label;
    }

    public Object getValue()
    {
        return _value;
    }

    public void setValue(Object value)
    {
        _value = value;
    }

    public boolean isEscape()
    {
        return _escape;
    }

    public void setEscape(boolean escape)
    {
        this._escape = escape;
    }
    
}
