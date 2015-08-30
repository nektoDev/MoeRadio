/**
 * Created by nektodev on 23.08.15.
 */

// Models
window.Artist = Backbone.Model.extend({});

window.ArtistCollection = Backbone.Collection.extend({
    model: Artist,
    url:"http://localhost:8080/api/artist"
});

// Views
window.ArtistListView = Backbone.View.extend({

    initialize:function () {
        this.template = _.template(tpl.get('artist-list'));
        this.model.bind("reset", this.render, this);
    },

    render:function (eventName) {
        var child = $(this.el);
        _.each(this.model.models, function (artist) {
            child.append(new ArtistListItemView({model:artist}).render().el);
        }, this);

        $(this.el).html(this.template({'child':child.html()}));
        return this;
    }
});

window.ArtistListItemView = Backbone.View.extend({

    tagName:"li",

    initialize:function () {
        this.template = _.template(tpl.get('artist-list-item'));
    },

    render:function (eventName) {
        $(this.el).html(this.template(this.model.toJSON()));
        return this;
    }

});

window.ArtistView = Backbone.View.extend({

    initialize:function () {
        this.template = _.template(tpl.get('artist-details'));
    },

    render:function (eventName) {
        $(this.el).html(this.template(this.model.toJSON()));
        return this;
    },

    close: function () {
        $(this.el).unbind();
        $(this.el).empty();
    }

});

// Router
var AppRouter = Backbone.Router.extend({

    routes:{
        "":"list",
        "artist/:id":"artistDetails"
    },

    list:function () {
        this.artistList = new ArtistCollection();
        var self = this;

        this.artistList.fetch({
            success: function(){
                this.artistListView = new ArtistListView({model: self.artistList});
                $('#sidebar').html(this.artistListView.render().el);

                if (self.requestedId) {
                    self.artistDetails(self.requestedId);
                } else {
                    var child = $(new ArtistView().el);

                    self.artistList.each(function (a) {
                        child.append(new ArtistView({model: a}).render().el);
                    });

                    $('#content').html(child);
                }

            }
        });

    },

    artistDetails:function (id) {
        if (id) {

            if (this.artistList) {
                this.artist = this.artistList.get(id);
                if (this.artistView) this.artistView.close();
                this.artistView = new ArtistView({model: this.artist});
                $('#content').html(this.artistView.render().el);
            } else {
                this.requestedId = id;
                this.list();
            }

        } else {
            this.list();
        }
    }
});



$(document).ready(function() {
    $.material.init();
    tpl.loadTemplates(['artist-list', 'artist-list-item', 'artist-details'], function() {

        app = new AppRouter();
        Backbone.history.start();
    });
});
